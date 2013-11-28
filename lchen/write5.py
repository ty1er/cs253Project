import datetime
from time import sleep
from voldemort import StoreClient

def write():
    c = StoreClient('test', [('n5', 6674)])
    for k in range(5, 2001, 5):
        c.put(str(k), str(datetime.datetime.now()));
    return True

write()
