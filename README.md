# Clojure GraphQL API

A GraphQL API implementation in Clojure using [walmartlabs/lacinia][1]. This is just a playground/demo application demonstrating how a project could be set up to cover all the aspects of it's lifecycle (development, testing, deployment etc.)

## TL;DR:

    // running
    lein run                # Start server. Go to http://localhost:8888/ to access GraphiQL
    
    // testing
    lein test               # run unit tests
    lein cloverage          # check code coverage

    // static analysis and validation
    lein ancient            # check the project for outdated dependencies and plugins
    lein eastwood           # run eastwood - clojure linter
    lein kibit              # static code analyzer for Clojure
    

## Getting started 


### Prerequisites

1. [Clojure 1.9][2]
2. [Leiningen][3]


### Installing 


### Running

    lein run               # Start the server. 
    
or

    lein repl               # Start REPL in `user` namespace
    user> (start)           # start server
    user> (stop)            # stop server

[Explore the API][5] via [GraphiQL][6] IDE.

## Testing


### Unit tests

    lein test               # run unit tests
    
## Contributing

1. Follow [Clojure Style Guide][4]
2. Check [#TL;DR:](#tl-dr-) section for static analysis and code validation commands
3. Git workflow - later...


## License

Copyright © 2019 Sven Laater

[1]: https://github.com/walmartlabs/lacinia
[2]: https://clojure.org/
[3]: https://leiningen.org/
[4]: https://github.com/bbatsov/clojure-style-guide
[5]: http://localhost:8888/
[6]: https://github.com/graphql/graphiql
