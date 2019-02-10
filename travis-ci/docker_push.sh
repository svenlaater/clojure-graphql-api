#! /bin/bash

# Push only if it's not a pull request
if [ -z "$TRAVIS_PULL_REQUEST" ] || [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
    # Allow docker images to be built only off master
    if [ "$TRAVIS_BRANCH" == "master" ] ; then
        # Make sure to have AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
        # defined as environment variables
        eval $(aws ecr get-login --region eu-central-1 --no-include-email | sed 's|https://||')
        docker push ${ECR}/${NAME}:${TAG}
    fi
fi
