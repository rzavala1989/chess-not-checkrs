# Chess Not Checkrs

## How to run

This application requires `Docker` and `Docker compose`. Please install them first.

Next, ensure that dotenv-cli is installed globally, and run the following command:
`npm install -g dotenv-cli`

- [Docker installation](https://www.docker.com/get-started)
- [Docker compose installation](https://docs.docker.com/compose/install/)

Then, run the following command:

```
sudo docker-compose up --build // on Mac
docker-compose up --build // on Windows

```

When `web-server` prints `Accepting connections at http://localhost:8080` and `ai-server` prints `AI Server is up and running. Ready to receive requests`, it is ready to be used. Then, visit [here](http://localhost:8080) to play chess against AI. Using `Chrome Browser` is recommended.

## Architecture

![Screenshot from 2022-06-27 19-22-16](https://user-images.githubusercontent.com/48105703/176066683-840572dc-ef22-4530-a42b-419d891c560d.png)

```

```
