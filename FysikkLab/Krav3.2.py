

import matplotlib.pyplot as plt
import csv
import math

curve_section=3.0
flat_section=math.inf
total_length_x_axis=10

def r_function(x_value):
    #The curve radius for each of the 3 sections, left curve, flat section and right curve.
    #Left and right section have equal radius
    dictValues={'1':curve_section,'2':flat_section,'3':curve_section}

    #Function to calculate which section the x-value is in
    #
    return dictValues[str(math.ceil(x_value*len(dictValues)/total_length_x_axis))]




print(r_function(2.5))