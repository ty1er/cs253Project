import datetime
from time import sleep
from voldemort import StoreClient, VoldemortException

def write():
    c = StoreClient('test', [('n5', 6674)])
    for k in range(5, 2001, 5):
        try:
            c.put(str(k), str(datetime.datetime.now()));
        except VoldemortException:
            print "Exception caught, retry..."
            sleep(1)
    return True

write()
#print missing

