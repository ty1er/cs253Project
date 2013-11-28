import datetime
from time import sleep
from voldemort import StoreClient

def write():
    c = StoreClient('test', [('n3', 6670)])
    for k in range(3, 2001, 5):
        c.put(str(k), str(datetime.datetime.now()));
    return True

def read():
    c = StoreClient('test', [('n3', 6670)])
    missing = []
    for k in range(1, 2001):
        val = c.get(str(k))
        if not val:
            #print k
            missing.append(k)
    return missing

def save(missing):
    data = open('data3.dat', 'w')
    for k in missing:
        data.write(str(k)+'\n')
    data.close()

write()
sleep(15)
missing = read()
save(missing)
#print missing

