package com.salonshop.dto;

import jakarta.validation.constraints.NotNull;

public record BookSlotRequest(
        @NotNull Long slotId
) {
}
