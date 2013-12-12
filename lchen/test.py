import datetime
from time import sleep
from voldemort import StoreClient

def write():
    c = StoreClient('test', [('n1', 6666)])
    for k in range(1, 501, 5):
        c.put(str(k), str(datetime.datetime.now()));
    return True

def read():
    c = StoreClient('test', [('n1', 6666)])
    missing = []
    for k in range(1, 501):
        val = c.get(str(k))
        if not val:
            #print k
            missing.append(k)
    return missing

def save(missing):
    data = open('data.dat', 'w')
    for k in missing:
        data.write(str(k)+'\n')
    data.close()

write()
sleep(5)
missing = read()
save(missing)
#print missing

