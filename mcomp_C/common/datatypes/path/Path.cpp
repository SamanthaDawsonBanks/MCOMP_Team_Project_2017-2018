/*
 * mcomp_C.ino
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *
 */
#include "Path.h"
#include "../Waypoint.h"

PathItem* NULL = nullptr;

int length;

Path::Path(Waypoint w) {
  this->destination = new PathItem(w);
  this->head = NULL;
  this->lastItem = NULL;
  this->length = 0;

}

Path::~Path() {
  // TODO Auto-generated destructor stub
}


void Path::addNode(Waypoint w){

	if(length > 0){
		(*lastItem).setNext(PathItem(w));
		*lastItem = (*lastItem).getNext();
	}
	else {
		head = new PathItem(w);
		lastItem = head;
	}

	length++;
}


Waypoint Path::poll(){

	Waypoint res = (*destination).getData();
	if(length > 0){
		res = (*head).getData();
		*head = (*head).getNext();

	if(head == NULL){
		lastItem = NULL;
	}
	length--;
	}
	return res;
}



int Path::getLength() {
	return length;
}


bool Path::isDone() {
     return true;
	 return(length == 0);

}



