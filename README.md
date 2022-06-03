# Student Project

## Entity

#### Student Entity

|Field name | Data Type| Description|
|-----|----|------------|
|id |UUID| unique identifier of student|
|name |String| name of student|
|gender | String| gender of student|
|createdDate | DateTime| date of creation of student|

#### Course Entity

|Field name | Data Type| Description|
|-----|----|------------|
|id |UUID| unique identifier of course|
|name |String| name of Course|

#### Book Entity

|Field name | Data Type| Description|
|-----|----|------------|
|id |UUID| unique identifier of book|
|name |String| name of book|
|dateOfPublish | Date | Date of book publishing|
|publicationHouse | String | name of publication house|

#### Author Entity

|Field name | Data Type| Description|
|-----|----|------------|
|id |UUID| unique identifier of author|
|name |String| name of author|
|gender | String| gender of author|

## APIs

#### Student API

|Http | Api| Description|
|-----|----|------------|
|POST |/api/students| Create Student|
|GET |/api/students/find| List all students by course name|

#### Course API

|Http | Api| Description|
|-----|----|------------|
|GET |/api/courses| Find all courses|
|GET |/api/courses/{courseId}/books| find books by course|

#### Book API

|Http | Api| Description|
|-----|----|------------|
|GET |/api/books/{bookId}/authors| find authors of book|

