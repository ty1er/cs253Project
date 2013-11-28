import datetime
from time import sleep
from voldemort import StoreClient

def read():
    c = StoreClient('test', [('n2', 6668)])
    missing = []
    for k in range(1, 2001):
        val = c.get(str(k))
        if not val:
            #print k
            missing.append(k)
    return missing

def save(missing):
    data = open('data2.dat', 'w')
    for k in missing:
        data.write(str(k)+'\n')
    data.close()

missing = read()
save(missing)
#print missing
