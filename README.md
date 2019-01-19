# Clojure GraphQL API

[![Build Status](https://travis-ci.org/svenlaater/clojure-graphql-api.svg?branch=master)](https://travis-ci.org/svenlaater/clojure-graphql-api)
[![codecov](https://codecov.io/gh/svenlaater/clojure-graphql-api/branch/master/graph/badge.svg)](https://codecov.io/gh/svenlaater/clojure-graphql-api)

A GraphQL API implementation in [Clojure][2] using [walmartlabs/lacinia][1]. This is just a playground/demo application demonstrating how a project could be set up to cover all the aspects of it's lifecycle (development, testing, CI/CD, documentaion etc.)

## TL;DR:

    // running
    lein run                # Start the server, open http://localhost:8888/ to access GraphiQL
    
    // testing
    lein test               # run unit tests
    lein cloverage          # check code coverage

    // static analysis and validation
    lein ancient            # check the project for outdated dependencies and plugins
    lein eastwood           # run eastwood - clojure linter
    lein kibit              # static code analyzer for Clojure
    

## Getting started 



### Prerequisites

1. [Leiningen][3]


### Running

Start the server with `lein run` and [Explore the API][5] via [GraphiQL][6] IDE or start REPL manually

    lein repl               # Start REPL in `user` namespace
    user> (start)           # start server
    user> (stop)            # stop server



#### [Docker][7] support

    lein uberjar                                           # Generate uberjar
    docker build -t clojure-graphql-api .                  # Build the image
    docker run -it --rm -p 8888:8888 clojure-graphql-api   # Run and open http://localhost:8888/

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
[7]: https://www.docker.com/
