import datetime
from time import sleep
from voldemort import StoreClient, VoldemortException

def read():
    c = StoreClient('test', [('n4', 6672)])
    missing = []
    for k in range(1, 2001):
        try:
            val = c.get(str(k))
            if not val:
            #print k
                missing.append(k)
        except VoldemortEcxeption:
            #print "Ecxeption caught, retry..."
            sleep(1)
    return missing

def save(missing):
    data = open('data4.dat', 'w')
    for k in missing:
        data.write(str(k)+'\n')
    data.close()

missing = read()
save(missing)

#write()
#print missing

