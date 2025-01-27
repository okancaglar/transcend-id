import { configureStore } from "@reduxjs/toolkit";
import refugeeReducer from "./refugeeSlice";
import registerResponseReducer from "./registerResponseSlice";
import locationReducer from "./locationSlice";
import validationReducer from "./validationSlice";

export const store = configureStore({
    reducer: {
        refugee: refugeeReducer,
        registerResponse: registerResponseReducer,
        locations: locationReducer,
        validation: validationReducer
    },
});
