:rocket: Build

`mvn clean test`


![Java CI](https://github.com/ProjectEKA/ProjectEKATests/workflows/Java%20CI/badge.svg?branch=master)

Env variable for local debugging
`Authorization` : `Bearer <Auth Token> ` with public_repo and workflow scope
`ConsentManagerSecret` : `Secret to generate JWT token with HIP service`

Environments

`env = ncg`
`env = nhsDev, nhsUAT, nhsSandbox `
