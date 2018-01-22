/*
 * PathItemPtr.cpp
 *
 *  Created on: 16 Jan 2018
 *      Author: David Avery
 */

#include "PathItemPtr.h"

PathItemPtr::PathItemPtr() {
  // TODO Auto-generated constructor stub
}

PathItemPtr::~PathItemPtr() {
  // TODO Auto-generated destructor stub
}

bool setNext(PathItem p) {
  next = p;
}

PathItem getNext() {
  return next;
}
