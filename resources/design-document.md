# Design Document

# _MCTracker Application_ Design

## 1. Problem Statement

**MCTracker** is an app that logs games for players and shows them their overall stats. Marvel Champions is a LCG (living card game) that you can play solo or up to 4 players. The app will allow players to log the Hero(s) they use, 
the villain they face, whether they won or lost, and what aspect(s) they played. It will also keep track of overall 
w/l stats.

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. Do I need to have a GSI table for all of the statistics?
2. Are the endpoints correctly mocked out?
3. The typos in endpoints
4. villain, hero, aspect name in the endpoint.


## 3. Use Cases

U1. _As a [MC player], I want to create a new game log for a MC game that I have played_

U2. _As a [MC player], I want to view a list of all my game logs_

U3. _As a [MC player], I want to view a single game log_

U4. _As a [MC player], I want to update a game log for a MC game using a subset of fields_

U5. _As a [MC player], I want to delete a game from my game logs_

U6. _As a [MC player], I want to view my overall w/l stats_



## 3.1 Stretch Use cases:

SU1. _As a [MC player], I want to be able to view my w/l stats with a specific hero_



# 4. Project Scope

### 4.1. In Scope

- Creating, retrieving, updating, and deleting a game log.
- Viewing all your game logs.
- Viewing your overall w/l stats.


### 4.2. Out of Scope

- The Stretch goals.
- Being able to purchase MC cards from our app.
- Organizing/keeping up with your MC collection.

# 5. Proposed Architecture Overview

This initial iteration will provide the minimum lovable product (MLP) including creating, retrieving, updating,
and deleting a game log for game log requests. It will also allow users to view their overall w/l stats.

We will use API Gateway and AWS Lambda to create the following endpoints:
- CreateGameLog
- GetGameLog
- GetAllGameLogs
- UpdateGameLog
- DeleteGameLog
- GetAllPlayerCharacters
- GetOverallStats


I will store GameLogs and player characters in separate DynamoDB tables.


# 6. API

## 6.1. Public Models

```
// GameLogModel
 
email // string 
gameId // string
date // string (converted date)
outcome // string
aspect // List<String>
hero // List<String>
villain // string


```


## 6.2 Endpoints

### 6.2.0 _Create GameLog Endpoint_
- Accepts `POST` requests to `/game_logs`
- Accepts data to create a new gameLog with a provided gameId, date, w/l outcome, aspect, hero,
  and villain. Returns the new GameLog, including a game ID assigned by the service.

### 6.2.1 _Get single GameLog Endpoint_
- Accepts `GET` requests to `/game_logs/:gameId`
- Takes the email from cognito
- Accepts an email and game ID and returns the corresponding GameLogModel.
    - If the given game ID is not found, will throw a `GameNotFoundException`

### 6.2.2 _Get all GameLogs by player Endpoint_
- Accepts `GET` requests to `/game_logs`
- Takes the email from cognito
- Accepts an email and returns the corresponding list of GameLogModels.
    - If the given game ID is not found, will throw a `GameNotFoundException`

### 6.2.3 _Get all PlayerCharacters Endpoint_
- Accepts `GET` requests to `/player_characters`
- Query's the playercharacters and returns all the characters for the dropdown menu.
- if the role parameter is provided, it will return all the characters for that role.

### 6.2.4 _Delete GameLog Endpoint_
- Accepts a `DELETE` request to `/game_logs/:gameId`
- Takes the email from cognito
- Accepts a game ID and returns the corresponding Deleted GameLogModel.
    - If the given game ID is not found, will throw a `GameNotFoundException`

### 6.2.5 _Update GameLog Endpoint_
- Accepts a `PUT` request to `/game_logs/:gameId`
- Takes the email from cognito
- Accepts a subset of data to update a gameLog and returns the updated gameLog.
    - If the given game ID is not found, will throw a `GameNotFoundException`
    - throws `UnauthorizedUserException` if attempted to be updated by an unauthorized user.

### 6.2.6 _Get Stats Endpoint_
- Accepts `GET` requests to `/stats`
- Takes the email from cognito
- Accepts an email and returns the overall w/l stats for that person's profile.



# 7. Tables


## 7.1 `gamelogs`
```
email // partition key, string
gameId // sort key, String
date // string (converted date)
outcome_wl // string
aspect // List<String>
hero // List<String>
villain // string

```

## 7.2 `playercharacters`
```
name // partition key, string
characterRole // sort key, string
```

### 7.1.2 `TotalW/LIndex` GSI table
```
email // partition key, String
outome_wl // sort key, string
```


# 8. Page storyboard

[Link to mock up board with all slides](https://jamboard.google.com/d/1rF50VtNMG0kGWcXDh3yenRobcF1Klmn7RgN8AbX6IkQ/viewer?f=0)