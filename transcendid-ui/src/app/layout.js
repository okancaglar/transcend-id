"use client";
import {ReduxProvider} from "@/redux/ReduxProvider";

export default function RootLayout({ children }) {
  return (
      <html lang="en">
      <head>
          <meta charSet="UTF-8"/>
          <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
          <title>TranscendId</title>
          <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'/>
      </head>
      <body>
      <ReduxProvider>{children}</ReduxProvider>
      </body>
      </html>
  );
}
