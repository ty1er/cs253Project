from pymongo import MongoClient
import datetime
client = MongoClient('n1', 27017)
db = client.test
print db.name
print db.my_collection

# set up replica set
client.admin.command("replSetInitiate")


