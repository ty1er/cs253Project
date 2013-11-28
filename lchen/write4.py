import datetime
from time import sleep
from voldemort import StoreClient

def write():
    c = StoreClient('test', [('n4', 6672)])
    for k in range(4, 2001, 5):
        c.put(str(k), str(datetime.datetime.now()));
    return True

write()

