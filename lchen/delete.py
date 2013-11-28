import datetime
from time import sleep
from voldemort import StoreClient

def dele():
    c = StoreClient('test', [('n1', 6666)])
    for k in range(1, 2001):
        c.delete(str(k))

dele()
print "Delete Done!"
#print missing

