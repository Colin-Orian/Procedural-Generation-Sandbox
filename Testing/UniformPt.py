"""
Algorithm to generate uniformly distributed points on a circle
Found here:https://www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php
"""
import math
import random
"""
Generates a random point uniformly in a circle
@param scale: scales the unit circle up or down
"""
def uniformPoints(scale):
    t = 2 * math.pi * random.random() #Randomly chosen angle

    # Adds two very thin triangles to create a parallelogram (ABCD)
    u = random.random() + random.random()

    r = 0.0
    #If point D is outside of the radius of the circle, fold it into the circle
    #Set radius to D
    if(u > 1):
        r = 2 - u
    else:
        r = u
    #r = 1
    xCoord = scale *r * math.cos(t)
    yCoord = scale * r * math.sin(t)
    return [xCoord, yCoord]
