from flask import Flask, request, jsonify
import re

app = Flask(__name__)

survivors = [
    {"id": 1, "name": "Rick", "survivor_id": "830914-1234", "infection_rate": 0, "inventory": ["WPN-001", "WPN-002", "MED-101", "FOD-005"]},
    {"id": 2, "name": "Ellie", "survivor_id": "040505-9090", "infection_rate": 20, "inventory": ["WPN-003", "MED-101", "SUT-700", "FOD-005"]},
    {"id": 3, "name": "Leon", "survivor_id": "980929-1111", "infection_rate": 0, "inventory": []},
    {"id": 4, "name": "Alice", "survivor_id": "750101-6666", "infection_rate": 99, "inventory": ["MED-101", "SUT-700", "FOD-005"]},
    {"id": 5, "name": "Glenn", "survivor_id": "881025-0001", "infection_rate": 0, "inventory": ["RAD-500", "BAT-100", "MAP-050", "WPN-001"]},
    {"id": 6, "name": "Levi", "survivor_id": "851225-0002", "infection_rate": 0, "inventory": ["WPN-002", "CPS-101", "GAS-999"]}
]

bunkers = [
    {"id": 1, "name": "Hilltop", "supplies_required": ["WPN-001", "MED-101", "FOD-005"]},
    {"id": 2, "name": "Lab", "supplies_required": ["KEY-999", "MED-101", "SUT-700"]},
    {"id": 3, "name": "Hospital", "supplies_required": ["MED-101", "SUT-700", "FOD-005"]},
    {"id": 4, "name": "SurveyCorps", "supplies_required": ["MAP-050", "WPN-002", "CPS-101", "SUT-700"]}
]

def _find_next_id():
    return max(s["id"] for s in survivors) + 1

@app.get("/survivor")
def get_survivors():
    return jsonify(survivors)

@app.get("/survivor/<id>")
def get_survivor(id):
    try:
        int(id)
    except:
         return {"error": "Survivor ID " + id + " is not formatted correctly (must be int)."}, 400

    for s in survivors:
        if s["id"] == int(id):
            return jsonify(s)
    return {"error": "Survivor ID " + id + " not found."}, 404 
 
@app.post("/register_survivor")
def register_survivor():
    if request.is_json:
        data = request.get_json()
        data["id"] = _find_next_id()

        if "name" not in data:
            return {"error": "No name provided"}, 400
        elif data["name"] == "" or data["name"] is None:
            return {"error": "Name cannot be empty"}, 400

        if "survivor_id" not in data:
            return {"error": "No survivor_id provided"}, 400
        
        pattern = re.compile(r'^\d{6}-\d{4}$')
        
        if pattern.match(data["survivor_id"]):
            month = int(data["survivor_id"][2:4])
            day = int(data["survivor_id"][4:6])
            
            if month == 0 or month > 12:
                return {"error": "Invalid month in survivor_id"}, 400
            
            if day == 0 or day > 30:
                return {"error": "Invalid day in survivor_id"}, 400 
        else:
            return {"error": "Malformed survivor_id: " + data["survivor_id"]}, 400
      
        if "infection_rate" not in data:
            return {"error": "No infection_rate provided"}, 400
        
        if not isinstance(data["infection_rate"], int):
             return {"error": "infection_rate must be an integer"}, 400
        
        if data["infection_rate"] < 0 or data["infection_rate"] > 100:
             return {"error": "infection_rate must be between 0 and 100"}, 400

        for existing in survivors:
            if data["survivor_id"] == existing["survivor_id"]:
                return {"error": "survivor_id already registered to Survivor " + str(existing["id"])}, 400

        if "inventory" in data:
            for item in data["inventory"]:
                if item == "" or item is None:
                    return {"error": "Empty item ID found"}, 400
                
                item_pattern = re.compile(r'^[A-Z]{3}-\d{3}$')
                if not item_pattern.match(item):
                    return {"error": "Malformed item ID: " + item}, 400 
        else:
            data["inventory"] = []

        allowed_keys = ["id", "survivor_id", "name", "inventory", "infection_rate"]
        for key in list(data.keys()):
            if key not in allowed_keys:
                del data[key]

        return data, 201
        
    return {"error": "Request must be JSON-formatted"}, 415

@app.put("/update_survivor/<id>")
def update_survivor(id):
    try:
        int(id)
    except:
         return {"error": "Survivor ID must be an integer."}, 400

    if request.is_json:
        data = request.get_json()
        
        if "name" in data and (data["name"] == "" or data["name"] is None):
             return {"error": "Name cannot be empty"}, 400
             
        if "inventory" in data:
            for item in data["inventory"]:
                item_pattern = re.compile(r'^[A-Z]{3}-\d{3}$')
                if not item_pattern.match(item):
                    return {"error": "Malformed item ID: " + item}, 400 
        
        if "infection_rate" in data:
            if not isinstance(data["infection_rate"], int):
                 return {"error": "infection_rate must be an integer"}, 400
            if data["infection_rate"] < 0 or data["infection_rate"] > 100:
                 return {"error": "infection_rate must be between 0 and 100"}, 400

        found_survivor = None
        for s in survivors:
            if s["id"] == int(id):
                found_survivor = s
                break
        
        if not found_survivor:
             return {"error": "Survivor ID " + id + " not found"}, 404

        if "survivor_id" in data and data["survivor_id"] != found_survivor["survivor_id"]:
             return {"error": "Changing survivor_id is prohibited."}, 400

        if "name" in data: found_survivor["name"] = data["name"]
        if "inventory" in data: found_survivor["inventory"] = data["inventory"]
        if "infection_rate" in data: found_survivor["infection_rate"] = data["infection_rate"]

        return found_survivor, 200
        
    return {"error": "Request must be JSON"}, 415

@app.delete("/kill_survivor/<id>")
def delete_survivor(id):
    try:
        target_id = int(id)
    except:
         return {"error": "ID format error"}, 400

    for i, s in enumerate(survivors):
        if s["id"] == target_id:
            del survivors[i]
            return {"status": "Survivor " + id + " removed."}
            
    return {"error": "Survivor " + id + " not found"}, 404 

@app.get("/bunker")
def get_bunkers():
    return jsonify(bunkers)

@app.get("/bunker/<id>")
def get_bunker(id):
    try:
        int(id)
    except:
         return {"error": "Bunker ID error"}, 400

    for b in bunkers:
        if b["id"] == int(id):
            return jsonify(b)
    return {"error": "Bunker ID " + id + " not found"}, 404 
        
@app.get("/can_enter/<survivor_id>/<bunker_id>")
def check_entry_clearance(survivor_id, bunker_id):
    try:
        s_id = int(survivor_id)
        b_id = int(bunker_id)
    except:
         return {"error": "IDs must be integers"}, 400

    target_survivor = next((s for s in survivors if s["id"] == s_id), None)
    target_bunker = next((b for b in bunkers if b["id"] == b_id), None)

    if not target_survivor:
        return {"error": "Survivor not found"}, 404
    if not target_bunker:
        return {"error": "Bunker not found"}, 404

    is_health_cleared = False
    infection_rate = target_survivor.get("infection_rate", 0)

    if infection_rate >= 50:
        is_health_cleared = False
    elif target_bunker["name"] == "Hospital":
        if infection_rate < 50:
            is_health_cleared = True
    else:
        if infection_rate == 0:
            is_health_cleared = True

    if not is_health_cleared:
        return {
            "access_granted": False,
            "reason": "Health check failed: Infection rate " + str(infection_rate) + "% not allowed in " + target_bunker["name"],
            "survivor": target_survivor["name"],
            "bunker": target_bunker["name"]
        }, 200

    matching_items = 0
    for required in target_bunker["supplies_required"]:
        if required in target_survivor["inventory"]:
            matching_items += 1
            
    is_safe = False
    if matching_items == len(target_bunker["supplies_required"]):
        is_safe = True

    return {
        "access_granted": is_safe, 
        "supplies_matched": matching_items,
        "required_total": len(target_bunker["supplies_required"]),
        "survivor": target_survivor["name"],
        "bunker": target_bunker["name"]
    }, 200

if __name__ == '__main__':
    app.run(debug=True)
