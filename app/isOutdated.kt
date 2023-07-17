val isOutdated = userUpdateDate?.before(developerUpdateDate) ?: false
val daysOutOfDate = if (isOutdated && userUpdateDate != null) {
    val currentTime = currentDate.time
    val updateDateTime = userUpdateDate.time
    val millisecondsPerDay = 24 * 60 * 60 * 1000 // Number of milliseconds in a day
    val timeDifference = currentTime - updateDateTime
    (timeDifference / millisecondsPerDay).coerceAtLeast(1)
} else {
    0 // Default value if conditions are not met
}
