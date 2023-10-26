# Design Document

# _MCTracker Application_ Design

## 1. Problem Statement

_**MCTracker** is an app that keeps track of your Marvel Champions collection and logs games for players.
It will allow players to log the Hero they use, the villain they face, whether they won or lost, and what aspect they played in.
It will also keep track of overall w/l stats and w/l stats with certain heroes._

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. Do I need to have a GSI table for all of the statistics?
2. Are the endpoints correctly mocked out?


## 3. Use Cases

U1. _As a [MC player], I want to create a new game log for a MC game that I just played_

U2. _As a [MC player], I want to view(get) a list of all my game logs_

U3. _As a [MC player], I want to view(get) a single game log_

U4. _As a [MC player], I want to update a game log for a MC game_

U5. _As a [MC player], I want to delete a game from my game logs_

U6. _As a [MC player], I want to view my w/l stats with a certain hero_

U7. _As a [MC player], I want to view my overall w/l stats_

U8. _As a [MC player], I want to view my w/l stats against certain villains_

U9. _As a [MC player], I want to view my w/l stats by the different aspect I played_


## 3.1 Stretch Use cases:

SU1. _As a [MC player], I want to view a list of my favorite heroes_

SU2. _As a [MC player], I want to query all my games in the game log by hero, aspect, villain, etc_



# 4. Project Scope

### 4.1. In Scope

- Creating, retrieving, updating, and deleting a game log.
- Viewing all your game logs.
- Viewing your overall w/l stats.
- Viewing your w/l stats with a certain hero or villain.
- Viewing your w/l stats with a certain aspect you played.


### 4.2. Out of Scope

- The Stretch goals.
- Being able to purchase MC cards from our app.
- Organizing/keeping up with your MC collection.

# 5. Proposed Architecture Overview

This initial iteration will provide the minimum lovable product (MLP) including creating, retrieving, updating,
and deleting a game log for game log requests. It will also allow users to view their overall w/l stats and their w/l
stats with certain heroes, villains and aspect.

We will use API Gateway and AWS Lambda to create the following endpoints:
- CreateGameLog
- GetGameLog
- GetAllGameLogs
- UpdateGameLog
- DeleteGameLog
- GetOverallStats
- GetHeroStats
- GetVillainStats
- GetAspectStats


I will store GameLogs and heroes and villains in separate DynamoDB tables.


# 6. API

## 6.1. Public Models

```
// GameLogModel
 
email // string 
gameId // string
date // string (converted date)
outcome // enum
aspect // enum
gameCharacters // List<PlayerCharacter>

```

```

// PlayerCharacterModel

name // string
roles // List<Enum>   

```


## 6.2 Endpoints

### 6.2.0 _Create GameLog Endpoint_
- Accepts `POST` requests to `/gamelogs`
- Accepts data to create a new gameLog with a provided gameId, date, w/l outcome, aspect, hero,
  and villain. Returns the new GameLog, including a game ID assigned by the service.

### 6.2.1 _Get single GameLog Endpoint_
- Accepts `GET` requests to `/gamelogs/:gameId`
- Takes the email from cognito
- Accepts an email and game ID and returns the corresponding GameLogModel.
    - If the given game ID is not found, will throw a `GameNotFoundException`

### 6.2.1.1 _Get all GameLogs by player Endpoint_
- Accepts `GET` requests to `/gamelogs/:`
- Takes the email from cognito
- Accepts an email and returns the corresponding list of GameLogModels.
    - If the given game ID is not found, will throw a `GameNotFoundException`

### 6.2.2 _Delete GameLog Endpoint_
- Accepts a `DELETE` request to `/gamelogs/:gameId`
- Takes the email from cognito
- Accepts a game ID and returns the corresponding Deleted GameLogModel.
    - If the given game ID is not found, will throw a `GameNotFoundException`

### 6.2.3 _Update GameLog Endpoint_
- Accepts a `PUT` request to `/gamelogs/:gameId`
- Takes the email from cognito
- Accepts data to update a gameLog and returns the updated gameLog.
    - If the given game ID is not found, will throw a `GameNotFoundException`
    - throws `UnauthorizedOwnerException` if attempted to be updated by an unauthorized user.

### 6.2.4 _Get Stats Endpoint_
- Accepts `GET` requests to `/gamelogs/:`
- Takes the email from cognito
- Accepts an email and returns the overall w/l stats for that person's profile.

### 6.2.5 _Get Hero Stats Endpoint_
- Accepts `GET` request to `/gamelogs/:hero`
- Takes the email from cognito
- Accepts an email and returns the w/l stats for a certain hero for that person's profile.

### 6.2.6 _Get Villain Stats Endpoint_
- Accepts `GET` request to `/gamelogs/:villain`
- Takes the email from cognito
- Accepts an email and returns the w/l stats for a certain villain for that person's profile.

### 6.2.7 _Get Aspect Stats Endpoint_
- Accepts `GET` request to `/gamelogs/:aspect`
- Takes the email from cognito
- Accepts an email and returns the w/l stats for a certain aspect for that person's profile.



# 7. Tables

_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what
objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the
`playlists` table in the Unit 3 project._

## 7.1 `gamelogs`
```
email // partition key, string
gameId // sort key, String
date // string (converted date)
outcome(w/l) // enum
aspect // enum
hero // PlayerCharacter
villain // PlayerCharacter

```

## 7.2 `playercharacters`
```
name // partition key, string
role // Enum
```

### 7.2.1 `TotalW/LIndex` GSI table
```
email // partition key, String
outome(w/l) // string
```

### 7.2.2 `TotalW/LHeroIndex` GSI table
```
email // partition key, String
hero // PlayerCharacter
outome(w/l) // string
```


# 8. Page storyboard

[Link to mock up board with all slides](https://jamboard.google.com/d/1rF50VtNMG0kGWcXDh3yenRobcF1Klmn7RgN8AbX6IkQ/viewer?f=0)