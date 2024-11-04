package lt.ancienttexts.domain;

import javax.validation.constraints.NotNull;

public record TabletRequest(
        @NotNull(message = "Title cannot be null")
        String title,

        @NotNull(message = "Description cannot be null")
        String description,

        @NotNull(message = "Location cannot be null")
        String location,

        byte[] image,

        @NotNull(message = "Translation cannot be null")
        String translation,

        @NotNull(message = "Tablet source cannot be null")
        String tabletSource,

        @NotNull(message = "Interpreted cannot be null")
        Boolean interpreted,

        @NotNull(message = "Date added cannot be null")
        String dateAdded,

        @NotNull(message = "Source link cannot be null")
        String sourceLink,

        @NotNull(message = "Dated at cannot be null")
        String datedAt
) {}
