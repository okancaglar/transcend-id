// src/redux/refugeeSlice.js
import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    uuid: "",
    publicKey: "",
    name: "",
    surname: "",
    ethnicity: "",
    creationTime: "",
    officerId: "",
};

const refugeeSlice = createSlice({
    name: "refugee",
    initialState,
    reducers: {
        setRefugee: (state, action) => {
            // Overwrite the entire refugee object
            return action.payload;
        },
        updateRefugee: (state, action) => {
            // Merge partial updates into the existing refugee
            return { ...state, ...action.payload };
        },
        clearRefugee: () => {
            // Reset to initial blank
            return { ...initialState };
        },
    },
});

export const { setRefugee, updateRefugee, clearRefugee } = refugeeSlice.actions;
export default refugeeSlice.reducer;
