/*
 * mcomp_C.ino
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *
 */
#include "PathItem.h"
#include "../Waypoint.h"


PathItem::PathItem(Waypoint w) {
   this->data = w;
   this->next = nullptr;
}

PathItem::~PathItem() {
  // TODO Auto-generated destructor stub
}

Waypoint PathItem::getData(){
	return data;
}

PathItem PathItem::getNext(){
	return *next;
}

bool PathItem::setNext(PathItem next){
	this->next = &next;
	return true;
}
