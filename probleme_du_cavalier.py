#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Feb 10 11:10:56 2023

@author: cbroussey yminaud
"""

from time import *

def cavalier(lig, col, w = 8, h = 8, cases = []):
    cases = cases + [[lig, col]]
    solution = []
    voisins = [[0, 0], [-2, 1], [-1, 2], [1, 2], [2, 1], [2, -1], [1, -2], [-1, -2], [-2, -1]]
    if len(cases) == w * h:
        solution = cases
    elif (cases[-1][0] <= w and cases[-1][0] > 0 and cases[-1][1] <= h and cases[-1][0] > 0):
        for i in voisins:
            nouveau = [cases[-1][0] + i[0], cases[-1][1] + i[1]]
            if (
                    not solution
                    and nouveau not in cases
                    and nouveau[0] > 0
                    and nouveau[0] <= w
                    and nouveau[1] > 0
                    and nouveau[1] <= h
                ):
                solution = cavalier(nouveau[0], nouveau[1], w, h, cases)
    return solution

def cavIte(lig, col, long, larg):
    solution = [[0, 0] for i in range(long * larg)]
    solution[0] = [lig, col]
    fini = False
    index = 0
    voisins = [[0, 0], [-2, 1], [-1, 2], [1, 2], [2, 1], [2, -1], [1, -2], [-1, -2], [-2, -1]] #lignes, colonnes
    while index < len(solution) and index >= 0:
        if (
            solution[index] not in solution[0:index]
            and solution[index][0] > 0
            and solution[index][0] <= long
            and solution[index][1] > 0
            and solution[index][1] <= larg
            ):
            lig = 0
            col = 0
        else:
            index -= 1
            lig = solution[index + 1][0] - solution[index][0]
            col = solution[index + 1][1] - solution[index][1]
            while index >= 0 and voisins.index([lig, col]) == len(voisins) - 1 :
                index -= 1
                lig = solution[index + 1][0] - solution[index][0]
                col = solution[index + 1][1] - solution[index][1]
            if index < 0:
                solution = []
        if index >= 0 and index < len(solution) - 1:
            solution[index + 1] = [solution[index][0] + voisins[voisins.index([lig, col]) + 1][0], solution[index][1] + voisins[voisins.index([lig, col]) + 1][1]]
        index += 1
    return solution

t = time()
a = cavalier(2, 2, 5, 5)
print(time() - t)
print(a)
t = time()
b = cavIte(2, 2, 5, 5)
print(time() - t)
print(b)
t = time()
c = cavalier(1, 2, 5, 5)
print(time() - t)
print(c)
t = time()
d = cavIte(1, 2, 5, 5)
print(time() - t)
print(d)
t = time()
e = cavalier(1, 1, 5, 5)
print(time() - t)
print(e)
t = time()
f = cavIte(1, 1, 5, 5)
print(time() - t)
print(f)