// src/redux/store.js
import { configureStore } from "@reduxjs/toolkit";
import refugeeReducer from "./refugeeSlice";
import registerResponseReducer from "./registerResponseSlice";
import locationReducer from "./locationSlice";

export const store = configureStore({
    reducer: {
        refugee: refugeeReducer,
        registerResponse: registerResponseReducer,
        locations: locationReducer,
    },
});
