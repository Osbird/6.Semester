import matplotlib.pyplot as plt
import csv
import math
import numpy as np
from numpy import pi, sin

curve_section = 0.82123
flat_section = 9999999
total_length_x_axis = 1

radiusOfTrack = curve_section
deltaTime = 1.0/10000.0
g = -9.8214675
m = 0.005

K = 0.000019

offset=total_length_x_axis/2



def r_function(x_value):
    # The curve radius for each of the 3 sections, left curve, flat section and right curve.
    # Left and right section have equal radius
    dictValues = {'0': curve_section, '1': flat_section, '2': curve_section}

    # Function to calculate which section the x-value is in
    if float(x_value+offset)%float(total_length_x_axis)<total_length_x_axis/3:
        return curve_section
    elif float(x_value+offset)%float(total_length_x_axis)<total_length_x_axis*2/3:
        return flat_section
    elif float(x_value+offset)%float(total_length_x_axis)<total_length_x_axis:
        return curve_section
    else:
        print("otside of slope in r_func")

    #number1to3=str(math.floor((int(x_value * len(dictValues)))%3 / total_length_x_axis))
    #if (int(number1to3) >2 or int(number1to3)< 0):
    #    print("r_function wrong")

    return dictValues[number1to3]

def nextSpeedEuler(v, x):
    return v + deltaTime*acceleration(x)-v*K

def acceleration(x):
    asin = math.asin(sinAngle(x))
    s = sinAngle(x)
    a = g*sinAngle(x)*(5.0/7.0)
    return g*sinAngle(x)*(5.0/7.0)

def sinAngle(x):
    #print("x test)")
    #print(x)
    if x>0.1:
        return float((x-0.1)/r_function(x))
    elif x<-0.1:
        return float((x+0.1) / r_function(x))
    return float(x/r_function(x))

def cosAngle(y,x):
    return (r_function(x) - y) / r_function(x)
#mgcos(a)x

def nextXPosision(v, x):
    angle = math.asin(sinAngle(x))
    arcLength = v * deltaTime
    angle += arcLength / r_function(x)
    return r_function(x) * math.sin(angle)

def importCSV(file):
    f = open(file, 'rt', encoding="ascii")
    reader = csv.reader(f, delimiter=';')
    i = 0
    values = []
    for row in reader:
        if i > 2:
            try:
                values.append([float(row[0])-0.026, float(row[1])/100-0.026, float(row[2])/100-0.026,
                          float(row[3])/100-0.026, float(row[4])/100-0.026])
            except:
                print("exept")
                None

        i += 1
    f.close()
    return values

def findStartMeasure(values):
    highestY = 0
    iY = 0
    for i in range(len(values)):
        if values[i][2] > highestY:
            highestY = values[i][2]
            iY = i
        else:
            print("Startmeasure Y")
            print(iY)
            return iY

def kinEnergi(v):
    return 0.5*m*v**2+(1.0/5.0)*m*v**2

def potEnergi(h):
    try:
        return -m*g*h
    except:
        print("PotEnergi")
        print(h)

def mekEnergi(h, v):
    return kinEnergi(v)+potEnergi(h)

# beregning

def plotCalculated(txt):
    tBeregnet = []
    xBeregnet = []
    yBeregnet = []
    vBeregnet = []
    EBeregnet = []
    KBeregnet = []
    UBeregnet = []
    statiskFriksjon = []
    NBeregnet = []

    values = importCSV(txt)

    startMeasure = findStartMeasure(values)
    print("Startmeasure")
    print(startMeasure)

    speed = 0
    print("\nValues")
    print(values)
    yPosition = values[startMeasure][2]
    xPosition = -1*math.sqrt(radiusOfTrack ** 2 - (radiusOfTrack - yPosition) ** 2)

    print("Testing r_func")
    tst=r_function(0)
    print(tst)

    for i in range(int(8.0/deltaTime)-startMeasure):
        tBeregnet.append(2*i/(2.0/deltaTime))

        xBeregnet.append(xPosition)

        sAngle = sinAngle(xPosition)
        #print(xPosition)
        yPosition = r_function(xPosition) - math.sqrt(r_function(xPosition) ** 2 - xPosition ** 2)
        yBeregnet.append(yPosition)

        vBeregnet.append(speed)

        #statiskFriksjon.append(g*sinAngle(xPosition) - acceleration(xPosition))
        statiskFriksjon.append(abs(-m*speed+m*g*sinAngle(xPosition)))

        NBeregnet.append((-m*g*cosAngle(yPosition,xPosition))+(m*speed**2)/r_function(xPosition))

        EBeregnet.append(mekEnergi(yPosition, speed))
        KBeregnet.append(kinEnergi(speed))
        UBeregnet.append(potEnergi(yPosition))

        speed = nextSpeedEuler(speed, xPosition)
        xPosition = nextXPosision(speed, xPosition)

    plt.plot(tBeregnet, xBeregnet)
    #plt.plot(tBeregnet, yBeregnet)

def posOrNegSpeed(x,y):
    if x>y:
        return x/abs(x)
    else:
        return y/abs(y)

def plotMeasured(txt):

    values = importCSV(txt)

    startMeasure = findStartMeasure(values)

    t = []
    x = []
    y = []
    v = []
    U = []
    K = []
    E = []

    t0 = values[startMeasure][0]

    for i in range(1000):
        if i >= startMeasure:
            t.append(values[i][0] - t0)

            x.append(values[i][1])

            yMalt = values[i][2]
            y.append(yMalt)

            #sign=posOrNegSpeed(values[i][1],values[i][2])
            vMalt = math.sqrt(values[i][3]**2 + values[i][4]**2)
            v.append(vMalt)

            U.append(potEnergi(yMalt))
            K.append(kinEnergi(vMalt))
            E.append(mekEnergi(yMalt, vMalt))

    plt.plot(t, v)


def plotSine():
    # Get x values of the sine wave

    time = np.arange(0, 7, 0.001)

    # Amplitude of the sine wave is sine of a variable like time

    amplitude = np.sin(time)

    # Plot a sine wave using time and amplitude obtained for the sine wave

    plt.plot(time, amplitude)

    # Give a title for the sine wave plot

def plotSine3():
    x = np.linspace(-0.1, 7, 1000)
    y1 = 0.0019*np.sin(3.2*x+5.2)+0.0019
    plt.plot(x, y1, '-b', label='sine')


#for i in range(42, 54):
plotCalculated("08.csv")
#plotCalculated("15.csv")
#plotCalculated("08.csv")
#plotCalculated("12.csv")
#plotCalculated("13.csv")
#plotCalculated("16.csv")
#plotCalculated("18.csv")
#plotCalculated("19.csv")
#plotCalculated("25.csv")


#plotMeasured("08.csv")
#plotMeasured("09.csv")
#plotMeasured("11.csv")
#plotMeasured("12.csv")
#plotMeasured("13.csv")
#plotMeasured("14.csv")
#plotMeasured("15.csv")
#plotMeasured("16.csv")
#plotMeasured("18.csv")
#plotMeasured("19.csv")
#plotMeasured("25.csv")
#plotMeasured("26.csv")
#plotMeasured("27.csv")
#plotMeasured("28.csv")
#plotSine()
# plotSine2()
#plotSine3()
plt.show()






