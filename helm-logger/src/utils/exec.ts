import { exec as nodeExec } from 'child_process';

export const exec = async (command: string): Promise<string> =>
  new Promise<string>((resolve, reject) => nodeExec(command, (error, stdout) => (error ? reject(error) : resolve(stdout))));
