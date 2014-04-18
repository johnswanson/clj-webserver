# clj-webserver

A Leiningen template for web applications, using Stuart Sierra's
[awesome](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded)
"reloaded" workflow. **All state** exists within `[[project-name]].system`.
For example, an instance of the storage protocol is created and initialized,
then passed to the ring handler. This way, every `(reset)` starts us out in a 
fresh state we know is valid.

## Usage

`lein new clj-webserver [[project-name]]`

## License

Copyright © 2014 John Swanson

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
