package dev.julian.minitestdspot.repository

import dev.julian.minitestdspot.data.*

class UserFactory {

    fun createUser() : User {
        return User(
            userId = 0,
            id = Id(
                name="AVS",
                value="756.1899.0072.04"
            ),
            name = Name(
                title="Madame",
                first="Lilly",
                last="Fernandez"
            ),
            location = Location(
                street = Street(
                    number=4688,
                    name="Rue Louis-Blanqui"
                ),
                city="La Tour-de-Peilz",
                state="Zürich",
                country="Switzerland",
                postcode="3162",
                coordinates = Coordinates(
                    latitude="-48.5682",
                    longitude="99.3579"
                ),
                timezone = TimeZone(
                    offset="+3:30",
                    description="Tehran"
                )
            ),
            email="lilly.fernandez@example.com",
            picture = Picture(
                large="https://randomuser.me/api/portraits/women/81.jpg",
                medium="https://randomuser.me/api/portraits/med/women/81.jpg",
                thumbnail="https://randomuser.me/api/portraits/thumb/women/81.jpg"
            )
        )
    }
}