#include "PathItem.h"

/*
 * PathItemPtr.h
 *
 *  Created on: 16 Jan 2018
 *      Author: David Avery
 */

#ifndef COMMON_DATATYPES_PATH_PATHITEMPTR_H_

#define COMMON_DATATYPES_PATH_PATHITEMPTR_H_


class PathItemPtr {
 public:
  PathItemPtr();
  virtual ~PathItemPtr();

  bool setNext(PathItem);
  PathItem getNext();

 private:
  PathItem next;
};

#endif /* COMMON_DATATYPES_PATH_PATHITEMPTR_H_ */
