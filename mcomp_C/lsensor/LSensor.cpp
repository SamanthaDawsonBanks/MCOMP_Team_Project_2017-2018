/*
 * LSensor.cpp
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 *      Author: Stephen Pope 15836791
 */

#include "LSensor.h"
#include "../config/robot_config.h"
//byte buffer[1980];
byte buffer[1980] = { 0xFA, 0xA0, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xA1, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xA2, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xA3, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xA4, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xA5, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xA6, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xA7, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xA8, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xA9, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xAA, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xAB, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xAC, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xAD, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xAE, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xAF, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xB0, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xB1, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xB2, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xB3, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xB4, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xB5, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xB6, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xB7, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xB8, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xB9, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xBA, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xBB, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xBC, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xBD, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xBE, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xBF, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xC0, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xC1, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xC2, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xC3, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xC4, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xC5, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xC6, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xC7, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xC8, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xC9, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xCA, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xCB, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xCC, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xCD, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xCE, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xCF, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xD0, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xD1, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xD2, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xD3, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xD4, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xD5, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xD6, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xD7, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xD8, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xD9, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xDA, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xDB, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xDC, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xDD, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xDE, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xDF, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xE0, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xE1, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xE2, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xE3, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xE4, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xE5, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xE6, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xE7, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xE8, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xE9, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xEA, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xEB, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xEC, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xED, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xEE, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xEF, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xF0, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xF1, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xF2, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xF3, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00, 0xFA, 0xF4, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00,
    0x00, 0xFA, 0xF5, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA,
    0xF6, 0x00, 0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xF7, 0x00,
    0x3C, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xF8, 0x00, 0x3C, 0xB8,
    0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8,
    0x0B, 0x00, 0x00, 0x00, 0x00, 0xFA, 0xF9, 0x00, 0x3C, 0xB8, 0x0B, 0x00,
    0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00, 0x00, 0xB8, 0x0B, 0x00,
    0x00, 0x00, 0x00 };

//buffer of bytes that takes in a packet of four reads from the LiDAR sensor
unsigned int distances[360];
Waypoint wp[360];

LSensor::LSensor() {
  pDistances = nullptr;
  inByte = 0;
  avgRPM = 0;
  targetRPM = 240;
  targetPWM = 71;  //71 rpm to hit as close as we can to the target rpm of 240
  AFMS1 = Adafruit_MotorShield(0x61);
  lidarMotor = (*AFMS1.getMotor(1));
  AFMS1.begin();
  lidarMotor.setSpeed(targetPWM);
  lidarMotor.run(FORWARD);
  SENSOR.begin(115200);             //init the input from LiDAR serial2
  Serial.begin(115200);
  Serial.println("Started");
}

LSensor::~LSensor() {
  //lidarMotor.setSpeed(0);
}

unsigned int LSensor::getAvgRPM() {
  avgRPM = 0;
  unsigned int res;            //RPM result
  int counter = 2;
  unsigned int lowerB;  //nth byte in the buffer, lower half of 16 bit little-endian value
  unsigned int upperB;  //n + 1 byte in the buffer, upper half of 16 bit little-endian value
  for (int i = 0; i < 90; i++) {
    res = 0;
    lowerB = buffer[counter];
    upperB = buffer[counter + 1];
    res = res | upperB;  //res (0) bitwise OR with b2 = b2 but in a 16 bit in not 8 bit byte
    res <<= 8;                       //shift bits 8 left to make space for b3
    res = res | lowerB;  //rpmLe (b2+8 zeros) logical OR with b3 = b2 concat b3 in one 16 bit value
    res >>= 6;  //Shift right 6 to remove the floating point (64th of an RPM) values
    Serial.println(res);
    avgRPM = avgRPM + res;
    counter = counter + 22;
  }
  avgRPM = avgRPM / 90;
  return avgRPM;                      //Returns RPM as whole number
}

/*
 * A single packet contains 4 reads.
 * A single read is 4 bytes long.
 * The first 14 bits(0-13) of the first 2 bytes are the actual distance data.
 * The remaining 2 bits are error states.
 * Bit 14 shows high if the return signal was weaker than expected.
 * Bit 15 shows high if the distance could not be calculated.
 * If bit 15 is high the read should be 0 and discounted.
 * The final 2 bytes are signal strength information and not worried about here.
 * The bit data for distance is stored in little endian format. Backwards to normal.
 * It needs to be flipped in order for the JVM on the other end to work with it.
 */
unsigned int LSensor::getRead(int location) {
  unsigned int res = 0;
  unsigned int lowerByte = buffer[location];
  unsigned int upperByte = buffer[location + 1];
  if (upperByte & 0x80) {  //Bitwise compare to see if did not calc flag was high
    return 0;
  } else {
    upperByte = upperByte & 0x7F;  //Removes weak signal flag
    res = res | upperByte;
    res <<= 8;
    res = res | lowerByte;
    //Serial.println(res, DEC);
    return res;
  }
}

unsigned int* LSensor::decodeRead() {
  int counter = 0;
  for (int i = 0; i < 90; i = i + 1) {
    counter = counter + 4; //advance to first read
    for (int j = 0; j < 4; j++) {
      distances[i * 4 + j] = getRead(counter);
      counter = counter + 4;
    }

    counter = counter + 2;  //To jump over the checksum bytes!

  }
  pDistances = &distances[0];
  return pDistances;
}

bool LSensor::adjustRPM() {
  getAvgRPM();
  if (avgRPM > targetRPM + 20) {  //if RPM is more than 10RPM off target, adjust it
    targetPWM = targetPWM - 1;
    lidarMotor.setSpeed(targetPWM);
    lidarMotor.run(FORWARD);
    return false;
  } else if (avgRPM < targetRPM - 20) {
    targetPWM = targetPWM + 1;
    lidarMotor.setSpeed(targetPWM);
    lidarMotor.run(FORWARD);
    return false;
  } else
    return true;
}

void LSensor::getEncodedRead() {
  if (SENSOR.available()) {
    inByte = SENSOR.read();
  }
  if (inByte == 0xFA) {            //Read a byte from Serial
    for (int i = 0; i < 1980; i++) {           //The head of a LiDAR packet read
      buffer[i] = SENSOR.read();  //Read the next bit in the serial and write it to next position in buffer
    }
  }
}

Waypoint* LSensor::toWaypoint() {
  //distance is 14bits
  //distance is in mm so max =16,383mm
  //convert to cm so dave can just div by 5 to make grid squares
  //so div by 10
  //then pos is array is theta, so the we can calc the y value using trig
  //the hyp and the opp gives us 2 sides of a triangle so we can SOHCAHTOA to find x
  //Waypoint constructor does this when handed an AngleDistance
  Serial.println("Start DATA: (T , D) - (X , Y)");
  for (int i = 0; i < 360; i++) {
    AngleDistance ad = AngleDistance((double) i,
                                     (long) (*(pDistances + i) / 10));

    Serial.print(ad.getTheta());
    Serial.print(" , ");
    Serial.print(ad.getDistance());
    Serial.print(" - ");

    Waypoint a = Waypoint(ad);
    wp[i] = a;
    Serial.print(a.getX());
    Serial.print(" , ");
    Serial.println(a.getY());
  }
  Waypoint* wpPtr = &wp[0];  //point to head of WP array
  return wpPtr;
}

Waypoint* LSensor::sense() {
  getEncodedRead();  //So we can dig out an accurate avgRPM
  while (adjustRPM() == false) {  //Keep adjusting RPM until within 10 of target
    getEncodedRead();
    adjustRPM();
  }
  getEncodedRead();  //The proper read
  decodeRead();  //Reverse reads so they are BigEndian and return pointer to head of array
  return toWaypoint();
}

Waypoint* LSensor::lSensorTest() {
  //TODO have a test buff

//  for (int i = 0; i < 1980; i++) {
//    buffer[i] = dummy[i];
//  }

//  getAvgRPM();
  decodeRead();
  return toWaypoint();
}

Waypoint* LSensor::lSensorTest(){
  buffer = {}; //TODO fill with LiDAR return for first mapLayer
  decodeRead();
  return toWaypoint();
}

