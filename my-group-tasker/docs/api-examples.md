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
  "activities":
  [
    {
      "activityId":1,
      "activityName":"Replace my sheet",
      "overdue": true
    },
    {
      "activityId":2,
      "activityName":"Clean the dishwasher filter",
      "overdue": null
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
  "associatedActivities": 5,
  "associatedTasks": 15,
  "associatedUncompletedTasks": 10
}
```

---


## Activities
### List activities
#### Request
```
GET /api/activities
```
#### Response example

```json
[
  {
    "activityId": 1,
    "activityName": "Replace my sheet",
    "groupId": 1,
    "groupName": "Chores",
    "activityOverdue": true,
    "schedule": [
      {
        "taskId": 7,
        "date": "2025-09-01",
        "overdue": true
      },
      {
        "taskId": 8,
        "date": "2025-09-20",
        "overdue": false
      }
    ],
    "lastCompleted": null,
    "daysSinceCompletion": null
  },
  {
    "activityId": 2,
    "activityName": "Clean the dishwasher filter",
    "groupId": 1,
    "groupName": "Chores",
    "activityOverdue": false,
    "schedule": null,
    "lastCompleted": ["2025-04-28"],
    "daysSinceCompletion": 50
  }
 ]
```
### Create an activity
#### Request
```
POST /api/activities
```
Body:

```json
{
  "activityName":"Clean the toilet",
  "groupId":1
}
```

#### Response examples
- 201: success
- 400: invalid input
- 409: conflict for duplicated activity names under the same group
- 500: internal server error

### Update an activity
#### Request
```
PUT /api/activities/1
```
Body:
```json
{
  "activityName":"Replace my blue sheet",
  "groupId": 2
}
```

#### Response example
- 200: success
- 400: invalid input
- 404: activity or group id not found
- 409: conflict for duplicated activity names under the same group
- 500: internal server error

### Partially update an activity
#### Request
```
PATCH /api/activities/1
```
Body:
```json
{
  "activityName":"Replace my Bluey sheet"
}
```

#### Response example
- 200: success
- 400: invalid input
- 404: activity or group id not found
- 409: conflict for duplicated activity names under the same group
- 500: internal server error

### Delete an activity
#### Request
```
DELETE /api/activities/2
```
#### Response example
- 200: success
- 404: activity id not found
- 500: internal server error

### Deletion impact check
#### Request
```
GET /api/activities/2/deletion-impact
```
#### Response example
```json
{
  "activityName":"Clean the toilet",
  "associatedTasks": 3,
  "associatedUncompletedTasks": 2
}
```
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