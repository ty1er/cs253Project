import datetime
from time import sleep
from voldemort import StoreClient, VoldemortException

def write():
    c = StoreClient('test', [('n1', 6666)])
    for k in range(1, 2001, 5):
        try:
            c.put(str(k), str(datetime.datetime.now()));
        except VoldemortException:
            print "Exception caught, retry..."
            sleep(15)
    return True

write()
#print missing

