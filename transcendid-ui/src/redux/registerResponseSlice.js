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
            return action.payload;
        },
        clearRegisterResponse: () => {
            return { ...initialState };
        },
    },
});

export const { setRegisterResponse, clearRegisterResponse } =
    registerResponseSlice.actions;
export default registerResponseSlice.reducer;
