# Api examples
RESTful API style is followed.








## Task groups
### List groups
#### Request
```
GET /api/groups
```
#### Response example


```json
[{
  "groupId":1,
  "groupName":"Chores",
  "tasks":
  [
    {
      "taskId":1,
      "taskName":"Replace my sheet"
    },
    {
      "taskId":2,
      "taskName":"Clean the dishwasher filter"
    }
  ]
}]
```


### Create a group
#### Request
```
POST /api/groups
```
Body:

```json
{
  "groupName":"Personal care"
}
```

#### Response examples
- 201: success
- 400: invalid input
- 409: conflict for duplicated group name
- 500: internal server error

### Update a group
#### Request
```
PUT /api/groups/2
```
Body:
```json
{
  "groupName":"Self-care"
}
```

#### Response example
- 200: success
- 400: invalid input
- 404: group id not found
- 409: conflict for duplicated group name
- 500: internal server error

### Delete a group
#### Request
```
DELETE /api/groups/2
```
#### Response example
- 200: success
- 404: group id not found
- 500: internal server error

### Deletion impact check
#### Request
```
GET /api/groups/2/deletion-impact
```
#### Response example
```json
{
  "groupName":"Self-care",
  "associatedTasks": 5,
  "associatedLogs": 15,
  "associatedSchedules": 10
}
```

---


## Tasks
### List tasks
#### Request
```
GET /api/tasks
```
#### Response example

```json
[
  {
    "taskId": 1,
    "taskName": "Replace my sheet",
    "groupId": 1,
    "groupName": "Chores",
    "schedule": ["2025-09-01","2025-09-20"],
    "lastCompleted": null,
    "daysSinceCompletion": null
  },
  {
    "taskId": 2,
    "taskName": "Clean the dishwasher filter",
    "groupId": 1,
    "groupName": "Chores",
    "schedule": null,
    "lastCompleted": ["2025-04-28"],
    "daysSinceCompletion": 50
  }
 ]
```
### Create a task
#### Request
```
POST /api/tasks
```
Body:

```json
{
  "taskName":"Clean the toilet",
  "groupId":1,
  "schedule": ["2025-09-01"]
}
```

#### Response examples
- 201: success
- 400: invalid input
- 409: conflict for duplicated task names under the same group
- 500: internal server error

### Update a task
#### Request
```
PUT /api/tasks/1
```
Body:
```json
{
  "taskName":"Replace my blue sheet",
  "groupId": 2,
  "schedule": ["2025-07-01"]
}
```

#### Response example
- 200: success
- 400: invalid input
- 404: task or group id not found
- 409: conflict for duplicated task names under the same group
- 500: internal server error

### Partially update a task
#### Request
```
PATCH /api/tasks/1
```
Body:
```json
{
  "taskName":"Replace my Bluey sheet"
}
```

#### Response example
- 200: success
- 400: invalid input
- 404: task or group id not found
- 409: conflict for duplicated task names under the same group
- 500: internal server error

### Delete a group
#### Request
```
DELETE /api/tasks/2
```
#### Response example
- 200: success
- 404: task id not found
- 500: internal server error

### Deletion impact check
#### Request
```
GET /api/tasks/2/deletion-impact
```
#### Response example
```json
{
  "taskName":"Clean the toilet",
  "associatedLogs": 3,
  "associatedSchedules": 2
}
```