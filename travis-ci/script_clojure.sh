#! /bin/bash


# install all the project dependencies
lein deps

# Run various static analysis tools
lein eastwood
lein ancient
lein kibit

# Run unit tests
lein test

# Generate and publish code coverage report
lein cloverage --codecov || true
bash <(curl -s https://codecov.io/bash)

# Build uberjar and create docker image
lein uberjar
