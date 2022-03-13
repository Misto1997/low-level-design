# Problem Statement
We have to do Low Level Design for cache system like Redis or Memcached.

## Main Features
- Write(Put): Insert a value against key in the Cache.
- Read(Get): Fetch a value against key in the Cache or null if not present in Cache.

## Other Features
- Eviction Policy: Remove key/value pair from cache in case, cache is full or if any other policy mechanism is there.
- Null Key is not allowed.