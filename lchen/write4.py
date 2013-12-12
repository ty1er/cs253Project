import datetime
from time import sleep
from voldemort import StoreClient, VoldemortException

def write():
    c = StoreClient('test', [('n4', 6672)])
    for k in range(4, 2001, 5):
        try:
            c.put(str(k), str(datetime.datetime.now()));
        except VoldemortException:
            print "Exception caught, retry..."
            sleep(1)
    return True

write()
#print missing

