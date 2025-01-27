import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    isValid: "",

};

const validationSlice = createSlice({
    name: "validation",
    initialState,
    reducers: {
        setValidation: (state, action) => {
            return action.payload;
        },
        clearValidation: () => {
            return  { ...initialState };
        },
    },
});

export const { setValidation, clearValidation } =
    validationSlice.actions;
export default validationSlice.reducer;
