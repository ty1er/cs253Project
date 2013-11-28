import datetime
from time import sleep
from voldemort import StoreClient

def write():
    c = StoreClient('test', [('n1', 6666)])
    for k in range(1, 2001, 5):
        c.put(str(k), str(datetime.datetime.now()));
    return True

write()

