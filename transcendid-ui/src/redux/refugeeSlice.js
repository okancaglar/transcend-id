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
            return action.payload;
        },
        updateRefugee: (state, action) => {
            return { ...state, ...action.payload };
        },
        clearRefugee: () => {
            return { ...initialState };
        },
    },
});

export const { setRefugee, updateRefugee, clearRefugee } = refugeeSlice.actions;
export default refugeeSlice.reducer;
