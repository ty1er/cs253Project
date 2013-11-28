import datetime
from time import sleep
from voldemort import StoreClient

def write():
    c = StoreClient('test', [('n2', 6668)])
    for k in range(2, 2001, 5):
        c.put(str(k), str(datetime.datetime.now()));
    return True

write()


