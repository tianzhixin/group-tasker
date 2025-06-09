# Api examples
RESTful API style is followed.


## Task groups
### List groups
#### Request
```
GET /api/groups?page=0&size=2
```
#### Response example

```json
{
  "content": [
    {
      "id": 1,
      "name": "Chores",
      "activities": [
        {
          "id": 1,
          "name": "Replace my sheet",
          "overdue": true
        },
        {
          "id": 2,
          "name": "Clean the dishwasher filter",
          "overdue": false
        }
      ]
    }
  ],
  "page": 0,
  "size": 2,
  "totalPages": 3,
  "totalElements": 5,
  "last": false  
}

```


### Create a group
#### Request
```
POST /api/groups
```
Body:

```json
{
  "name":"Personal care"
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
  "name":"Self-care"
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
  "name":"Self-care",
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
GET /api/activities?page=0&size=2
```
#### Response example

```json
{
  "content": [
    {
      "id": 1,
      "name": "Replace my sheet",
      "group": {
        "id": 1,
        "name": "Chores"
      },
      "overdue": true,
      "schedule": [
        {
          "taskId": 7,
          "dueDate": "2025-09-01",
          "overdue": true
        },
        {
          "taskId": 8,
          "dueDate": "2025-09-20",
          "overdue": false
        }
      ],
      "lastCompleted": null,
      "daysSinceCompletion": null
    },
    {
      "id": 2,
      "name": "Clean the dishwasher filter",
      "group": {
        "id": 1,
        "name": "Chores"
      },
      "overdue": false,
      "schedule": null,
      "lastCompleted": [
        "2025-04-28"
      ],
      "daysSinceCompletion": 50
    }
  ],
  "page": 0,
  "size": 2,
  "totalPages": 3,
  "totalElements": 5,
  "last": false
}

```
### Create an activity
#### Request
```
POST /api/activities
```
Body:

```json
{
  "name":"Clean the toilet",
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
  "name":"Replace my blue sheet",
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
  "name":"Replace my Bluey sheet"
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
  "name":"Clean the toilet",
  "associatedTasks": 3,
  "associatedUncompletedTasks": 2
}
```

---


## Tasks
### List tasks
#### Request
```
GET /api/tasks?page=0&size=3
```
#### Response example

```json
{
  "content": [
    {
      "id": 3,
      "activity": {
        "id": 1,
        "name": "Replace my sheet"
      },
      "group": {
        "id": 1,
        "name": "Chores"
      },
      "dueDate": "2025-05-28",
      "status": "overdue",
      "daysOverdue": 30,
      "completionDate": null,
      "daysSinceCompletion": null
    },
    {
      "id": 7,
      "activity": {
        "id": 2,
        "name": "Clean the dishwasher filter"
      },
      "group": {
        "id": 1,
        "name": "Chores"
      },
      "dueDate": null,
      "status": "completed",
      "daysOverdue": null,
      "completionDate": "2025-03-28",
      "daysSinceCompletion": 90
    },
    {
      "id": 9,
      "activity": {
        "id": 2,
        "name": "Clean the dishwasher filter"
      },
      "group": {
        "id": 1,
        "name": "Chores"
      },
      "dueDate": "2025-08-1",
      "status": "to-do",
      "daysOverdue": null,
      "completionDate": null,
      "daysSinceCompletion": null
    }
  ],
  "page": 0,
  "size": 3,
  "totalPages": 3,
  "totalElements": 8,
  "last": false
}
```
### List tasks under an activity
#### Request
```
GET /api/activities/7/tasks?page=0&size=2
```
#### Response example

```json
{
  "content": [
    {
      "id": 7,
      "activity": {
        "id": 2,
        "name": "Clean the dishwasher filter"
      },
      "group": {
        "id": 1,
        "name": "Chores"
      },
      "dueDate": null,
      "status": "completed",
      "daysOverdue": null,
      "completionDate": "2025-03-28",
      "daysSinceCompletion": 90
    },
    {
      "id": 9,
      "activity": {
        "id": 2,
        "name": "Clean the dishwasher filter"
      },
      "group": {
        "id": 1,
        "name": "Chores"
      },
      "dueDate": "2025-08-1",
      "status": "to-do",
      "daysOverdue": null,
      "completionDate": null,
      "daysSinceCompletion": null
    }
  ],
  "page": 0,
  "size": 2,
  "totalPages": 3,
  "totalElements": 5,
  "last": false
}

```

### Create a task
#### Request
```
POST /api/task
```
Body:

```json
{
  "activityId":5,
  "dueDate": "2025-08-1",
  "completionDate": null
}
```

#### Response examples
- 201: success
- 400: invalid input
- 500: internal server error

### Update a task
#### Request
```
PUT /api/tasks/1
```
Body:
```json
{
  "activityId":8,
  "dueDate": "2025-08-1",
  "completionDate": "2025-07-01"
}
```

#### Response example
- 200: success
- 400: invalid input
- 404: task or activity id not found
- 500: internal server error

### Partially update a task
#### Request
```
PATCH /api/tasks/1
```
Body:
```json
{
  "completionDate":"2025-07-02"
}
```
#### Response example
- 200: success
- 400: invalid input
- 404: task id not found
- 500: internal server error

### Mark a task as completed today
#### Request
```
PATCH /tasks/123/complete
```
#### Response example
- 200: success
- 404: task id not found
- 500: internal server error


### Delete a task
#### Request
```
DELETE /api/tasks/2
```
#### Response example
- 200: success
- 404: task id not found
- 500: internal server error



