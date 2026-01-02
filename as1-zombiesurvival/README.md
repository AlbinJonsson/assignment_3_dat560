# Zombie Apocalypse Survival System

The primary purpose of this system is to check whether survivors are eligible to enter specific safe bunkers based on their health status and equipment.

This system has a REST API that surfaces the following functions:

- *Endpoint, REST Method, Example URL, Description*
- /survivor, GET, http://127.0.0.1:5000/survivor, Get a list of all survivors, with their IDs.
- /survivor/{id}, GET, http://127.0.0.1:5000/survivor/6, Get data for a single survivor.
- /register_survivor, POST, http://127.0.0.1:5000/register_survivor, Register a new survivor record in the database, if a record does not already exist for that survivor_id.
- /update_survivor/{id}, PUT, http://127.0.0.1:5000/update_survivor/2, Update a survivor record. Note that changes to survivor_id are not allowed.
- /kill_survivor/{id}, DELETE, http://127.0.0.1:5000/kill_survivor/5, Remove a survivor record.
- /bunker, GET, http://127.0.0.1:5000/bunker, Get a list of all bunkers, with their IDs.
- /bunker/{bunker_id}, GET, http://127.0.0.1:5000/bunker/1, Get a list of required supplies for a particular bunker.
- /can_enter/{survivor_id}/{bunker_id}, GET, http://127.0.0.1:5000/can_enter/1/1, Checks whether a particular survivor is allowed to enter a particular bunker.

The POST and PUT methods require, as input, a JSON structure representing a survivor. The allowed records in this structure include:

- name (string)
- survivor_id (string, format YYMMDD-NNNN) (i.e., personnummer)
- infection_rate (integer, 0 to 100, 0 and 100 are included)
- inventory (a list of supplies, each an ID represented by a string of format “XXX-NNN”, where “XXX” is a three-letter item code and NNN is a three-number code).

The survivor records stored in the app also include the field:

- id (integer).

An id is not needed for the create method, as it is assigned by the system. 

A bunker is represented by a list of required supplies, where - again - each has an item ID. To be granted entry, a survivor must possess every single item listed in the bunker's requirements. Bunkers also have specific health regulations regarding infection rates - the Hospital bunker allows entry for survivors with an infection rate below 50, whereas other bunkers strictly require a rate of 0.

All input is validated by the system. If you provide invalid or malformed input - either in the endpoint URL or the JSON bodies for the register and update methods - you should expect an appropriate error.

Note that the POST/PUT/DELETE methods do not actually make permanent changes in this example. You will get an appropriate response, but the record will not actually be created, updated, or deleted. You can use the result body to verify the results of running these functions.

To deploy the system locally:

- Check out the repository
- In a terminal:
    - Enter the directory containing app.py
    - Install the Python package flask: python -m pip install flask 
    - Set the following environmental variables: 
        - export FLASK_APP=app.py
        - export FLASK_ENV=development
        - (on Windows, “set” instead of “export”)
    - Start flask: flask run

Once the system is deployed, you can interact with the system using curl, Postman, or other utilities that can send requests to the endpoints defined above. 

See the following tutorial for more information: https://realpython.com/api-integration-in-python/#rest-and-python-tools-of-the-trade 
