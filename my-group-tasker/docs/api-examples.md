# Api examples
RESTful API style is followed.
## Groups
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
### Add a group
#### Request
```
POST /api/groups
```
Body:

```json
{
  "groupId":2, 
  "groupName":"Personal care"
}
```

#### Response examples
- 201: success
- 400: invalid input
- 409: conflict for duplicated group name
- 500: internal server error

### Modify a group
#### Request
```
PUT /api/groups
```
Body:
```json
{
  "groupId":2, 
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
- 409: conflict for duplicated group name
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
