import React, { createContext, useState } from "react";

export const TokenContext = React.createContext({
  token: "",
  setToken: (token) => {},
});
export const NameContext = React.createContext({
  name: "",
  setName: (name) => {},
});
export const IdContext = React.createContext({
  id: "",
  setId: (id) => {},
});
export const TypeContext = React.createContext({
  type: "",
  setType: (type) => {},
});
export const MailContext = React.createContext({
  mail: "",
  setMail: (mail) => {},
});