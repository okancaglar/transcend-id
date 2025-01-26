// src/redux/locationSlice.js
import { createSlice } from "@reduxjs/toolkit";

const initialState = [
    // Example: { immigrantId: "", location: "", timestamp: "", officerId: "" }
];

const locationSlice = createSlice({
    name: "locations",
    initialState,
    reducers: {
        setLocations: (state, action) => {
            // Overwrite the entire array
            return action.payload;
        },
        addLocation: (state, action) => {
            // Push a new location object
            state.push(action.payload);
        },
        clearLocations: () => {
            return [];
        },
    },
});

export const { setLocations, addLocation, clearLocations } =
    locationSlice.actions;
export default locationSlice.reducer;
