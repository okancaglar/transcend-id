// src/redux/registerResponseSlice.js
import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    uuid: "",
    privateKey: "",
};

const registerResponseSlice = createSlice({
    name: "registerResponse",
    initialState,
    reducers: {
        setRegisterResponse: (state, action) => {
            // Overwrite the entire registerResponse
            return action.payload;
        },
        clearRegisterResponse: () => {
            // Reset to initial blank
            return { ...initialState };
        },
    },
});

export const { setRegisterResponse, clearRegisterResponse } =
    registerResponseSlice.actions;
export default registerResponseSlice.reducer;
