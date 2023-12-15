import { defHttp } from '/@/utils/http/axios';

import type SearchParameters from '../SearchParameters';
import type PageResult from '../PageResult';
import type #(name.pascal.singular)Entity from './#(name.pascal.singular)Entity';

const domain = '/#(name.kebab.plural)';

export function list#(name.pascal.singular)(params: SearchParameters) {
  return defHttp.get<PageResult<#(name.pascal.singular)Entity>>({ url: domain, params }, { joinParamsToUrl: true });
}

export function load#(name.pascal.singular)(id: #(id.type.ts)) {
  return defHttp.get<#(name.pascal.singular)Entity>({ url: `${domain}/${id}` });
}

export function create#(name.pascal.singular)(data: #(name.pascal.singular)Entity) {
  return defHttp.post<#(name.pascal.singular)Entity>({ url: domain, data });
}

export function update#(name.pascal.singular)(id: #(id.type.ts), data: #(name.pascal.singular)Entity) {
  return defHttp.put<null>({ url: `${domain}/${id}`, data });
}

export function delete#(name.pascal.singular)(id: #(id.type.ts)) {
  return defHttp.delete<null>({ url: `${domain}/${id}` });
}

export { #(name.pascal.singular)Entity };
